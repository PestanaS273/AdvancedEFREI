<script setup>
  import { useI18n } from 'vue-i18n'
  const { t } = useI18n()
</script>
<template>
    <div class="bg-white shadow rounded-lg overflow-hidden">
      <table class="min-w-full border-collapse">
        <thead class="bg-gray-200">
          <tr>
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">ID</th>
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase"> {{ t('Question') }}</th>
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Answer') }}</th>

          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(questionResponse, index) in questionReponses"
            :key="questionReponses.id"
            :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'"
          >
          <td class="px-6 py-4 text-sm text-gray-700">{{ questionResponse.id }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ questionResponse.question }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ questionResponse.response }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>

import formServices from '../../services/form.services';



  export default {
    data() {
      return {
        questionReponses: [],
      };
    },
    methods: {
      async getForm() {
        const response = await formServices.getForm(this.$route.params.id);
        console.log(response);
        console.log(response.questionReponses);
        this.questionReponses = response.questionReponses || [];
        return response;
      }

    },
    async created() {
      this.data = await this.getForm();
      for (let i = 0; i < this.data.questionReponses.length; i++) {
        console.log(this.data.questionReponses[i].id);
      }
    }
  };
  </script>
  