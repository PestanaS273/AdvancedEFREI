<script setup>
import { useI18n } from 'vue-i18n'
const { t } = useI18n()
import store from '../../store/index'

const setCollectionForm = (form) => {
  store.commit('collectionForms/setCollectionForms', form)
  console.log('Selected Form:', form)
}

const setCoursId = (id) => {
  store.commit('cours/setCoursId', id)
  console.log('Selected CoursId:', id)
}

</script>
<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <table class="min-w-full border-collapse">
      <thead class="bg-gray-200">
        <tr>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">ID</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase"> {{ t('Title') }}</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Answers') }}</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Creation Date') }}</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase"></th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(survey, index) in data"
          :key="survey.id"
          :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'"
        >
          <td class="px-6 py-4 text-sm text-gray-700">{{ survey.id }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ survey.nomCours }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ survey.count }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ survey.created }}</td>
          <td><RouterLink :to="{ name: 'collection-feedback', params: { id: survey.id}}" class="text-sm font-medium text-gray-600 uppercase" @click="setCoursId(survey.idCours)">{{ t('Review Answers')}}</RouterLink></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {



  props: {
        data: {
          type: Array,
          required: true,
        },
  },
};
</script>
