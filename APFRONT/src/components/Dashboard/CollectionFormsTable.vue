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
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase"> {{ t('Student') }}</th>
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Creation Date') }}</th>
            <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase"></th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(form, index) in data"
            :key="form.id"
            :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'"
          >
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.id }}</td>
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.student }}</td>
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.created }}</td>
            <td><RouterLink :to="{ name: 'admin-feedback-review', params: { id: form.id}}" class="text-sm font-medium text-gray-600 uppercase">Review Answers</RouterLink></td>
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
  