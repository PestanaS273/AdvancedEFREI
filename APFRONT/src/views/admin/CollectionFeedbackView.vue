<script setup>
import CollectionFormsTable from '../../components/Dashboard/CollectionFormsTable.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const breadcrumbs = [
  { name: 'Dashboard', path: '/dashboard' },
  { name: 'Collection Forms', path: route.path },
];

</script>

<template>
    <Breadcrumb :breadcrumbs="breadcrumbs" />
    <h1 class="text-3xl font-bold text-gray-800 mb-6">Dashboard</h1>

    <CollectionFormsTable :data="forms" />
</template>

<script>
export default {
    components: { CollectionFormsTable, Breadcrumb },
    data() {
      return {
      forms: [
          { id: 1, student: "Sebastian", created: "2024-11-01" },
          { id: 2, student: "Adam", created: "2024-11-05" },
        ],
      };
  },
};


</script>


<style lang="scss" scoped>

</style>