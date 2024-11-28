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
            v-for="(form, index) in data"
            :key="form.id"
            :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'"
          >
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.id }}</td>
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.question }}</td>
            <td class="px-6 py-4 text-sm text-gray-700">{{ form.answer }}</td>
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
  