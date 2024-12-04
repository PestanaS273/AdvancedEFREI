<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
    <div class="min-h-screen bg-blue-100 overflow-hidden">
        <div class="container mx-auto my-36">
            <div>

                <div class="bg-white relative shadow rounded-lg w-5/6 md:w-5/6  lg:w-4/6 xl:w-3/6 mx-auto">
                    <div v-if="user.imageProfile" class="flex justify-center">
                        <img :src="user.imageProfile" alt="" class="rounded-full mx-auto absolute -top-20 w-32 h-32 shadow-md border-4 border-white transition duration-200 transform hover:scale-110">
                    </div>
                    <div v-else class="flex justify-center">
                            <img src="https://avatars0.githubusercontent.com/u/35900628?v=4" alt="" class="rounded-full mx-auto absolute -top-20 w-32 h-32 shadow-md border-4 border-white transition duration-200 transform hover:scale-110">
                    </div>
                    
                    <div class="mt-16">
                        <h1 class="font-bold text-center text-3xl text-gray-900">{{ user.nom }}</h1>
                        <p class="text-center text-sm text-gray-400 font-medium">{{t('Data Engineering student')}}</p>
                        <p>
                            <span>
                                
                            </span>
                        </p>
                        <div class="w-full">
                            <h3 class="font-medium text-gray-900 text-left px-6">{{t('Profile informations')}}</h3>
                            <div class="mt-5 w-full flex flex-col items-center overflow-hidden text-sm">
                                <span class="w-full border-t border-gray-100 text-gray-900 py-4 pl-6 pr-3 w-full block hover:bg-gray-100 transition duration-150 inline">
                                        <p class= "inline">{{t('Email')}}: </p>
                                        <p class="inline">{{ user.email }} </p>
                                </span>

                                <span class="w-full border-t border-gray-100 text-gray-900 py-4 pl-6 pr-3 w-full block hover:bg-gray-100 transition duration-150 inline">
                                        <p class= "inline">{{t('Phone number')}}: </p>
                                        <p class="inline">{{ user.numTel }}</p>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<script>
import utilisateurServices from '../services/utilisateur.services';
import store from '../store/index';

export default {
    name: 'profileView',
    data() {
        return {
            user: [],

        }
    },

    methods: {
        async getUser() {
            const user = store.getters['user/getUser'];
            console.log(user.email);
            try {
                const response = await utilisateurServices.getUser(user.email);
                console.log(response);
                this.user = response;
                console.log(this.user.nom)
            } catch (error) {
                console.error(error);
            }
        },
    },
    async created() {
        this.getUser();
    }
}

</script>

<style>

</style>