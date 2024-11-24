<script setup>
import { ref } from 'vue';
import { useI18n } from 'vue-i18n'
import AddUserListBtn from './AddUserListBtn.vue'
import AddTeacherListBtn from './AddTeacherListBtn.vue'

const { t } = useI18n()

const showAddUserMenu = ref(false);

function toggleAddUserMenu() {
    showAddUserMenu.value = !showAddUserMenu.value;
}

const courses = [
  { name: 'Matemáticas' },
  { name: 'Física' },
  { name: 'Historia' },
  { name: 'Literatura' },
  { name: 'Química' }
];

const teachers = [
  { name: 'Profesor A' },
  { name: 'Profesor B' },
  { name: 'Profesor C' },
  { name: 'Profesor D' },
  { name: 'Profesor E' }
];
</script>

<template>
    <div>
        <button @click="toggleAddUserMenu" class="fixed bottom-4 left-6 flex flex-col items-center justify-center w-35 h-35 text-black">
            <img src="../../assets/user.svg" alt="Create new Form" class="w-20 h-20 mb-1" />
            <span class="text-md">Add New Users</span>
        </button>

        <div v-if="showAddUserMenu" class="fixed inset-0 flex items-center justify-center z-50">
            <div class="fixed inset-0 bg-black opacity-50"></div>
            <div class="bg-white p-8 rounded-lg shadow-lg z-10 w-3/4 max-w-3xl h-3/4">
                <h2 class="text-4xl mb-4">{{ t('Update Users') }}</h2>
                <h3 class="text-2xl mb-4 text-gray-700">{{ t('Update Students') }}</h3>
                <AddUserListBtn />
                <AddTeacherListBtn />
                <form @submit.prevent="submitForm">
                    <div class="mb-6">
                        <label for="course" class="block text-2xl font-medium text-gray-700">{{  t('Lesson') }}</label>
                        <select id="course" class="mt-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                        <option v-for="course in courses" :key="course.name" :value="course.name">{{ course.name }}</option>
                        </select>
                    </div>
                    <div class="mb-6">
                        <label for="teacher" class="block text-2xl font-medium text-gray-700">{{ t('Teacher') }}</label>
                        <select id="teacher" class="mt-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                        <option v-for="teacher in teachers" :key="teacher.name" :value="teacher.name">{{ teacher.name }}</option>
                        </select>
                    </div>
                    <!-- Agrega más campos según sea necesario -->
                    <div class="flex justify-end">
                        <button type="button" @click="toggleAddUserMenu" class="mr-4 bg-gray-500 text-white px-4 py-2 rounded-md">{{t('Cancel')}}</button>
                        <button type="submit" class="bg-indigo-600 text-white px-4 py-2 rounded-md">{{ t('Submit')}}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'AddUser',
    data() {
        return {

        }
    },

    methods: {

    }
}

</script>

<style scoped>

</style>