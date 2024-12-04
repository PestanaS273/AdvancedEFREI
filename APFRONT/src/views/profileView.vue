<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
<body>
    <div class="container mx-auto my-60">
        <div>

            <div class="bg-white relative shadow rounded-lg w-5/6 md:w-5/6  lg:w-4/6 xl:w-3/6 mx-auto">
                <div class="flex justify-center">
                        <img src="https://avatars0.githubusercontent.com/u/35900628?v=4" alt="" class="rounded-full mx-auto absolute -top-20 w-32 h-32 shadow-md border-4 border-white transition duration-200 transform hover:scale-110">
                </div>
                
                <div class="mt-16">
                    <h1 class="font-bold text-center text-3xl text-gray-900">Jean Dupont</h1>
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
                                    <p class="inline">jean.dupont@efrei.net </p>
                            </span>

                            <span class="w-full border-t border-gray-100 text-gray-900 py-4 pl-6 pr-3 w-full block hover:bg-gray-100 transition duration-150 inline">
                                    <p class= "inline">{{t('Phone number')}}: </p>
                                    <p class="inline">07 12 34 56 78</p>
                            </span>

                            <span class="w-full border-t border-gray-100 text-gray-900 py-4 pl-6 pr-3 w-full block hover:bg-gray-100 transition duration-150 inline">
                                    <p class= "inline">{{t('Address')}}: </p>
                                    <p class="inline">25 boulevard champagne, Paris</p>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>

</template>

<style>

</style>