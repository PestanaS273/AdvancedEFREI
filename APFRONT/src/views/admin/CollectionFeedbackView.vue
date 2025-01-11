<script setup>
import CollectionFormsTable from '../../components/Dashboard/CollectionFormsTable.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const breadcrumbs = [
  { name: 'Dashboard', path: '/' },
  { name: 'Collection Forms', path: route.path },
];

</script>

<template>
    <Breadcrumb :breadcrumbs="breadcrumbs" />
    <h1 class="text-3xl font-bold text-gray-800 mb-6">Dashboard</h1>

    <CollectionFormsTable :data="forms" />
</template>

<script>
import formServices from '../../services/form.services';

export default {
    components: { CollectionFormsTable, Breadcrumb },
    data() {
      return {
      forms: [
        ],
      };
  },

  methods: {
    async getFormsForCourse() {
      try {
        const id = await this.getCoursId();
        console.log('CoursId:', id);
        const response = await formServices.getFormsForCourse(id);
        console.log(response);
        this.forms = response;
      } catch (error) {
        console.error(error);
      }
    },
    async getCoursId() {
      return this.$store.state.cours.coursId;
    }
  },

  async created() {
    await this.getFormsForCourse();

  },
};


</script>


<style lang="scss" scoped>

</style>