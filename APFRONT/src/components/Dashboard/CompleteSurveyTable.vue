<script setup>
import { ref, onMounted } from 'vue';
import apiClient from '../../services/api'; 
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
// const questions = ref([]);
const questions = [
    { id: 1, question: 'What is your name?', type: 'text' },
    { id: 2, question: 'How would you rate our service?', type: 'rating' },
    { id: 3, question: 'What is your favorite color?', type: 'text' },
    { id: 4, question: 'How would you rate our product?', type: 'rating' },
]
const answers = ref({});

const fetchQuestions = async () => {
  try {
    const response = await apiClient.get('/questions'); 
    questions.value = response.data;
  } catch (error) {
    console.error('Error fetching questions:', error);
  }
};

const setAnswer = (id, value) => {
  answers.value[id] = value;
};

const submitAnswers = async () => {
  try {
    const response = await apiClient.post('/submit-answers', answers.value); 
    console.log('Answers submitted successfully:', response.data);
  } catch (error) {
    console.error('Error submitting answers:', error);
  }
};

onMounted(() => {
  fetchQuestions();
});
</script>

<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <table class="min-w-full border-collapse">
      <thead class="bg-gray-200">
        <tr>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">ID</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Question') }}</th>
          <th class="px-6 py-3 text-left text-sm font-medium text-gray-600 uppercase">{{ t('Answer') }}</th>
        </tr>
      </thead>
      <tbody v-if="questions.length > 0">
        <tr
          v-for="(question, index) in questions"
          :key="question.id"
          :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'"
        >
          <td class="px-6 py-4 text-sm text-gray-700">{{ question.id }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ question.question }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">
            <template v-if="question.type === 'text'">
              <textarea
                v-model="answers[question.id]"
                class="w-full p-2 border border-gray-300 rounded-md resize-y"
                :placeholder="t('Your answer')"
              ></textarea>
            </template>
            <template v-else-if="question.type === 'rating'">
              <div class="flex space-x-2">
                <div
                  v-for="n in 5"
                  :key="n"
                  @click="setAnswer(question.id, n)"
                  :class="{
                    'w-8 h-8 border border-gray-300 rounded-md cursor-pointer flex items-center justify-center': true,
                    'bg-blue-100': answers[question.id] === 1,
                    'bg-blue-200': answers[question.id] === 2,
                    'bg-blue-300': answers[question.id] === 3,
                    'bg-blue-400': answers[question.id] === 4,
                    'bg-blue-500': answers[question.id] === 5,
                    'bg-gray-200': answers[question.id] !== n
                  }"
                >
                  {{ n }}
                </div>
              </div>
            </template>
          </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr>
          <td colspan="3" class="px-6 py-4 text-center text-sm text-gray-700">{{ t('No questions available') }}</td>
        </tr>
      </tbody>
    </table>
    <div class="p-6">
      <button
        @click="submitAnswers"
        class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
      >
        {{ t('Submit') }}
      </button>
    </div>
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





