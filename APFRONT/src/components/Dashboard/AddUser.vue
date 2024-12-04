<script setup>
import { ref, computed, onMounted } from 'vue';
import { useI18n } from 'vue-i18n'
import AddUserListBtn from './AddUserListBtn.vue'
import AddTeacherListBtn from './AddTeacherListBtn.vue'

const { t } = useI18n()

const showAddUserMenu = ref(false);

function toggleAddUserMenu() {
    showAddUserMenu.value = !showAddUserMenu.value;
}
</script>

<template>
    <div>
        <button @click="toggleAddUserMenu" class="fixed bottom-4 left-6 flex flex-col items-center justify-center w-35 h-35 text-black">
            <img src="../../assets/user.svg" alt="Create new Form" class="w-20 h-20 mb-1" />
            <span class="text-md">{{t('Add New Users')}}</span>
        </button>

        <div v-if="showAddUserMenu" class="fixed inset-0 flex items-center justify-center z-50 ">
            <div class="fixed inset-0 bg-black opacity-50"></div>
            <div class="bg-white p-8 rounded-lg shadow-lg z-10 w-3/4 max-w-3xl h-3/4 overflow-auto">
                <h2 class="text-4xl mb-4">{{ t('Update Users') }}</h2>
                <h3 class="text-2xl mb-4 text-gray-700">{{ t('Update Students') }}</h3>
                <AddUserListBtn />
                <h3 class="text-2xl mb-4 text-gray-700">{{ t('Update Teachers') }}</h3>
                <AddTeacherListBtn />
                <form @submit.prevent="submitForm">
                    <div class="my-6">
                        <h3 class="text-2xl mb-4 font-medium text-gray-700">{{ t('Add New Admin') }}</h3>
                        <div class="mb-6">
                            <div class="mt-2">
                                <label class="inline-flex items-center text-xl mb-4 mx-4">
                                    <input type="radio" name="user" value="student" v-model="selectedUser" class="form-radio text-indigo-500">
                                    <span class="ml-2">{{ t('Student') }}</span>
                                </label>
                                <label class="inline-flex items-center text-xl mb-4 mx-4">
                                    <input type="radio" name="user" value="teacher" v-model="selectedUser" class="form-radio text-indigo-500">
                                    <span class="ml-2">{{ t('Teacher') }}</span>
                                </label>
                            </div>
                        </div>
                        <label class="inline-flex items-center text-xl mb-4 mx-4">{{ t('Select User') }}</label>
                        <select id="user" class="mt-2 block w-full mx-4 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-md">
                            <option v-for="user in userList" :key="user.id" :value="user.id">{{ user.prenom }} {{ user.nom }}</option>
                        </select>
                    </div>
                    <!-- Agrega más campos según sea necesario -->
                    <div class="flex justify-end">
                        <button type="button" @click="toggleAddUserMenu" class="mr-4 bg-gray-500 text-white px-4 py-2 rounded-md">{{ t('Cancel') }}</button>
                        <button type="submit" class="bg-indigo-600 text-white px-4 py-2 rounded-md">{{ t('Submit') }}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import utilisateurServices from '../../services/utilisateur.services';

export default {
    name: 'AddUser',
    components: { AddUserListBtn, AddTeacherListBtn },
    data() {
        return {
            teachers: [],
            students: [],
            selectedUser: 'student',
        }
    },
    computed: {
        userList() {
            return this.selectedUser === 'student' ? this.students : this.teachers;
        }
    },
    methods: {
        async getAllStudents() {
            try {
                const response = await utilisateurServices.getAllStudents();
                console.log(response);
                this.students = response;
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
        toggleAddUserMenu() {
            this.showAddUserMenu = !this.showAddUserMenu;
        },
        async submitForm() {
            // Lógica para enviar el formulario
        }
    },
    created() {
        this.getAllStudents();
        this.getAllTeachers();
    }
}
</script>

<style scoped>
</style>