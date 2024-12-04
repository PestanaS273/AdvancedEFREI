<script setup>
import AnswerTable from '../../components/Dashboard/AnswerTable.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { useI18n } from 'vue-i18n'

import { useRoute } from 'vue-router';

const route = useRoute();

const breadcrumbs = [
  { name: 'Dashboard', path: '/dashboard' },
  { name: 'Review Form', path: route.path },

];
const { t } = useI18n()

</script>

<template>
    <Breadcrumb :breadcrumbs="breadcrumbs" />
    <h1 class="text-3xl mx-4 font-bold text-gray-800 mb-6">Sebastian - English Form</h1>
    

    <AnswerTable :data="forms" />
</template>

<script>
export default {
    components: { AnswerTable, Breadcrumb },
    data() {
      return {
      forms: [
          { id: 1, question: "Points", answer: "5"  },
          { id: 2, question: "Grade", answer: "4" },
        ],
      };
  },

};


</script>


<style lang="scss" scoped>

</style>