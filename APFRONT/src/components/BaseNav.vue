<script setup>
import { ref } from 'vue'
import { Dialog, DialogPanel } from '@headlessui/vue'
import { Bars3Icon, XMarkIcon } from '@heroicons/vue/24/outline'
import LanguageSwitcher from './LanguageSwitcher.vue'
import { useI18n } from 'vue-i18n'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import ConnexionService from '../services/autherization.services'

const { t } = useI18n()
const store = useStore()
const router = useRouter()

const mobileMenuOpen = ref(false)

const logout = async () => {
  try {
    await ConnexionService.logout();
    store.dispatch('user/logout');
    router.push({ name: 'login' });
  } catch (error) {
    console.error(error);
  }
}
</script>

<template>
    <header class=" inset-x-0 top-0 z-50">
        <nav class="flex items-center justify-between p-6 lg:px-8 bg-white" aria-label="Global">
          <div class="flex lg:flex-1">
            <RouterLink to="/dashboard" class="-m-1.5 p-1.5">
              <span class="sr-only">EFREI</span>
              <img class="h-8 w-auto" src="../assets/efrei_logo.png" alt="" />
            </RouterLink>
          </div>
          <div class="flex lg:hidden">
            <button type="button" class="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700" @click="mobileMenuOpen = true">
              <span class="sr-only">Open main menu</span>
              <Bars3Icon class="size-6" aria-hidden="true" />
            </button>
          </div>
          <div class="hidden lg:flex lg:gap-x-12">
            <RouterLink to="/dashboard" class="text-sm/6 font-semibold text-gray-900">{{ t('Dashboard') }}</RouterLink>
            <RouterLink to="/dashboard" class="text-sm/6 font-semibold text-gray-900">{{ t('Evaluations') }}</RouterLink>
            <RouterLink to="/dashboard" class="text-sm/6 font-semibold text-gray-900">{{ t('Teachers') }}</RouterLink>
            <RouterLink to="/profile" class="text-sm/6 font-semibold text-gray-900">{{ t('Profile') }}</RouterLink>
          </div>
          <div class="hidden lg:flex lg:flex-1 lg:justify-end">
            <button @click="logout()" class="text-sm/6 font-semibold text-gray-900">{{ t('Logout')}}</button>
          </div>
          <div class="hidden lg:flex lg:flex-1 lg:justify-end overflow">
            <LanguageSwitcher />
          </div>
        </nav>
        <Dialog class="lg:hidden" @close="mobileMenuOpen = false" :open="mobileMenuOpen">
          <div class="fixed inset-0 z-50" />
          <DialogPanel class="fixed inset-y-0 right-0 z-50 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
            <div class="flex items-center justify-between">
              <a href="#" class="-m-1.5 p-1.5">
                <span class="sr-only">EFREI</span>
                <img class="h-8 w-auto" src="../assets/efrei_logo.png" alt="" />
              </a>
              <button type="button" class="-m-2.5 rounded-md p-2.5 text-gray-700" @click="mobileMenuOpen = false">
                <span class="sr-only">Close menu</span>
                <XMarkIcon class="size-6" aria-hidden="true" />
              </button>
            </div>
            <div class="mt-6 flow-root">
              <div class="-my-6 divide-y divide-gray-500/10">
                <div class="space-y-2 py-6">
                  <a class="-mx-3 block rounded-lg px-3 py-2 text-base/7 font-semibold text-gray-900 hover:bg-gray-50">{{ t('Lesson') }}</a>
                  <a class="-mx-3 block rounded-lg px-3 py-2 text-base/7 font-semibold text-gray-900 hover:bg-gray-50">{{ t('Evaluations') }}</a>
                  <a class="-mx-3 block rounded-lg px-3 py-2 text-base/7 font-semibold text-gray-900 hover:bg-gray-50">{{ t('Teachers') }}</a>
                  <a class="-mx-3 block rounded-lg px-3 py-2 text-base/7 font-semibold text-gray-900 hover:bg-gray-50">{{ t('Profile') }}</a>

                </div>
                <div class="py-6">
                  <LanguageSwitcher />
                </div>
                <div class="py-6">
                  <button @click="logout" class="-mx-3 block rounded-lg px-3 py-2.5 text-base/7 font-semibold text-gray-900 hover:bg-gray-50">{{ t('Logout')}}</button>
                </div>
              </div>
            </div>
          </DialogPanel>
        </Dialog>
    </header>
</template>




