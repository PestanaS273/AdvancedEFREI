<script setup>
import AnswerTable from '../../components/Dashboard/AnswerTable.vue';
import Breadcrumb from '../../components/Breadcrumb.vue';
import { useI18n } from 'vue-i18n'

import { useRoute } from 'vue-router';
import CompleteSurveyTable from '../../components/Dashboard/CompleteSurveyTable.vue';

const route = useRoute();

const breadcrumbs = [
  { name: 'Dashboard', path: '/' },
  { name: 'Review Form', path: route.path },

];
const { t } = useI18n()

</script>

<template>
    <Breadcrumb :breadcrumbs="breadcrumbs" />
    <h1 class="text-3xl mx-4 font-bold text-gray-800 mb-6">{{ form.etudiant.prenom }} - {{ form.cours[0].nomCours }}</h1>
    

    <CompleteSurveyTable :data="forms" />
</template>

<script>
import formServices from '../../services/form.services';

export default {
    components: { CompleteSurveyTable, Breadcrumb },
    data() {
      return {
      form: [

        ],
      };
  },

methods: {
  async getForm() {

    const response = await formServices.getForm(this.$route.params.id);
    console.log(response);
    return response;
  }

},
async created() {
  this.form = await this.getForm();
}

};


</script>


<style lang="scss" scoped>

</style>