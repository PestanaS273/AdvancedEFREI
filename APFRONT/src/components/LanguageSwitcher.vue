<template>
    <div>
        <button @click="setLanguage('en')">English</button>
        <button @click="setLanguage('fr')">Français</button>

    </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'
import { watchEffect } from 'vue';

const { locale } = useI18n()

const setLanguage = (newLang) => {
    locale.value = newLang
    localStorage.setItem('lang', newLang)
}

watchEffect(() => {
    const storedLang = localStorage.getItem('lang')
    if (storedLang) {
        locale.value = storedLang
    }
})

</script>