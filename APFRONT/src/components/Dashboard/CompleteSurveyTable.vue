<script setup>
import { ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute } from 'vue-router';
import formServices from '../../services/form.services';
import responseServices from '../../services/reponse.services';

const { t } = useI18n();
const route = useRoute();
const questionReponses = ref([]);
const answers = ref({});

const setAnswer = (id, value) => {
  answers.value[id] = value;
};

const saveResponse = async () => {
  const unanswered = questionReponses.value.filter(q => !answers.value[q.id]);
  if (unanswered.length > 0) {
    alert(`Las siguientes preguntas no tienen respuesta: ${unanswered.map(q => q.id).join(', ')}`);
    return;
  }


  try {
    await Promise.all(
      questionReponses.value.map(question => 
        responseServices.saveResponse(question.id, answers.value[question.id])
      )
    );
    alert('Todas las respuestas se han guardado correctamente.');
  } catch (error) {
    console.error('Error guardando respuestas:', error);
    alert('Hubo un error al guardar las respuestas. Inténtalo de nuevo.');
  }
};

const getForm = async () => {
  try {
    const response = await formServices.getForm(route.params.id);
    questionReponses.value = response.questionReponses || [];
  } catch (error) {
    console.error('Error cargando formulario:', error);
  }
};

onMounted(getForm);
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
      <tbody v-if="questionReponses.length > 0">
        <tr v-for="(question, index) in questionReponses" :key="question.id" :class="index % 2 === 0 ? 'bg-gray-50' : 'bg-white'">
          <td class="px-6 py-4 text-sm text-gray-700">{{ question.id }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">{{ question.question }}</td>
          <td class="px-6 py-4 text-sm text-gray-700">
            <template v-if="question.type === 'rating'">
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
            <template v-else>
              <textarea v-model="answers[question.id]" class="w-full p-2 border border-gray-300 rounded-md resize-y" :placeholder="t('Your answer')"></textarea>
            </template>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="p-6">
      <button @click="saveResponse" class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">
        {{ t('Submit') }}
      </button>
    </div>
  </div>
</template>
