<script setup>
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import formServices from '../../services/form.services';
import coursServices from '../../services/cours.services';

const { t } = useI18n();

const showAddFormMenu = ref(false);
const selectedTemplate = ref('');
const supplementaryQuestions = ref([]);
const newQuestion = ref('');
const showSupplementaryQuestions = ref(false);
const anonymSurvey = ref(false);

function toggleAddFormMenu() {
  showAddFormMenu.value = !showAddFormMenu.value;
}

function addSupplementaryQuestion() {
  if (newQuestion.value.trim() !== '') {
    supplementaryQuestions.value.push(newQuestion.value);
    newQuestion.value = '';
  }
}

function removeSupplementaryQuestion(index) {
  supplementaryQuestions.value.splice(index, 1);
}

async function submitForm() {
    const templateQuestions = templates[selectedTemplate.value].map(q => q.question);
    const allQuestions = [...templateQuestions, ...supplementaryQuestions.value];

    const coursID = document.getElementById('course').value;
    console.log('El cours id es', coursID);

    try {
        const students = await coursServices.getStudentsFromCours(coursID);
        console.log('Students:', students);
        for (const studentId of students) {
            const formData = {
                course: coursID,
                teacher: 10,
                student: studentId,
                questions: allQuestions,
                anonymSurvey: anonymSurvey.value
            };
            const response = await formServices.addForm(formData);
            console.log('Form submitted:', response);
        }
        toggleAddFormMenu();
    } catch (error) {
        console.error('Error submitting form: ',error);
    }



}

const templates = {
  basic: [
    { question: '¿Cómo calificarías la clase?' },
    { question: '¿Qué mejorarías en la clase?' }
  ],
  long: [
    { question: '¿Cómo calificarías al profesor?' },
    { question: '¿Qué sugerencias tienes para el profesor?' }
  ]
};
</script>

<template>
    <div>
        <button @click="toggleAddFormMenu" class="fixed bottom-6 right-6 flex flex-col items-center justify-center w-35 h-35 border-2 border-black rounded-full text-blue-500">
            <img src="../../assets/plus.svg" alt="Create new Form" class="w-20 h-20 mb-1" />
            <!-- <span class="text-xs">Create new Form</span> -->
        </button>

        <div v-if="showAddFormMenu" class="fixed inset-0 flex items-center justify-center z-50">
            <div class="fixed inset-0 bg-black opacity-50"></div>
            <div class="bg-white p-8 rounded-lg shadow-lg z-10 w-3/4 h-3/4 max-w-2xl">
                <h2 class="text-4xl mb-8">{{ t('Create New Form') }}</h2>
                
                <form @submit.prevent="submitForm">
                    <div class="flex space-x-4">
                        <div class="mb-6 w-1/2">
                            <label for="course" class="block text-2xl font-medium text-gray-700">{{ t('Lesson') }} :</label>
                            <select id="course" class="mt-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                                <option v-for="course in cours" :key="course.nomCours" :value="course.id">{{ course.nomCours }}</option>
                            </select>
                        </div>
                        <div class="mb-6 w-1/2">
                            <label for="teacher" class="block text-2xl font-medium text-gray-700">{{ t('Teacher') }} :</label>
                            <select id="teacher" class="mt-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                                <option v-for="teacher in teachers" :key="teacher.nom" :value="teacher.id">{{ teacher.prenom }} {{ teacher.nom }}</option>
                            </select>
                        </div>
                    </div>
                    <div class="mb-6">
                        <label class="block text-2xl font-medium text-gray-700">{{ t('Select Template') }}</label>
                        <div class="mt-2">
                        <label class="inline-flex items-center">
                            <input type="radio" name="template" value="basic" v-model="selectedTemplate" class="form-radio text-indigo-500">
                            <span class="ml-2">{{ t('Basic Template') }}</span>
                        </label>
                        <label class="inline-flex items-center ml-6">
                            <input type="radio" name="template" value="long" v-model="selectedTemplate" class="form-radio text-indigo-500">
                            <span class="ml-2">{{ t('Long Template') }}</span>
                        </label>
                        </div>
                    </div>
                    <div class="flex space-x-4">
                        <div class="mb-6 w-max">
                            <label class="inline-flex items-center cursor-pointer" > {{ t('Anonym Survey') }} ?
                                <input type="checkbox" v-model="anonymSurvey" value="" class="sr-only peer ">
                                <div class=" ml-10 relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300  rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-indigo-600"></div>
                            </label>
                        </div>
                    </div>

                    <div class="flex space-x-4">
                        <div class="mb-6 w-max">
                            <label class="inline-flex items-center cursor-pointer" > {{ t('Add Supplementary Questions') }} ?
                                <input type="checkbox" v-model="showSupplementaryQuestions" value="" class="sr-only peer ">
                                <div class=" ml-10 relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300  rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-indigo-600"></div>
                            </label>
                        </div>
                    </div>

                    <div v-if="showSupplementaryQuestions" class="mb-6">
                        <div v-for="(question, index) in supplementaryQuestions" :key="index" class="flex items-center mb-2">
                        <input type="text" v-model="supplementaryQuestions[index]" class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                        <button type="button" @click="removeSupplementaryQuestion(index)" class="ml-2 bg-gray-500 text-white px-2 py-1 rounded-md">{{t('Remove')}}</button>
                        </div>
                        <div class="flex items-center">
                        <input type="text" v-model="newQuestion" placeholder="New Question" class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                        <button type="button" @click="addSupplementaryQuestion" class="ml-2 bg-indigo-500 text-white px-4 py-2 rounded-md">{{t('Add')}}</button>
                        </div>
                    </div>
                    

                    <div class="flex justify-end">
                        <button type="button" @click="toggleAddFormMenu" class="mr-4 bg-gray-500 text-white px-4 py-2 rounded-md">{{ t('Cancel') }}</button>
                        <button type="submit" class="bg-indigo-800 text-white px-4 py-2 rounded-md">{{ t('Submit') }}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import coursServices from '../../services/cours.services';
import utilisateurServices from '../../services/utilisateur.services';

export default {
    name: 'AddForm',
    data() {
        return {
            AddFormMenu: false,
            cours: [],
            teachers: [],
        }
    },

    methods: {
        showAddForm() {
            this.AddFormMenu = !this.AddFormMenu
        },
        async getAllCours() {
            try {
                const response = await coursServices.getAllCours();
                console.log(response);
                this.cours = response;
            } catch (error) {
                console.error(error);
            }
        },
        async getAllTeachers() {
            try {
                const response = await utilisateurServices.getAllTeachers();
                console.log(response);
                this.teachers = response;
            } catch (error) {
                console.error(error);
            }
        },

    },
    async created() {
        this.getAllCours();
        this.getAllTeachers();
    }
}

</script>

<style scoped>

</style>