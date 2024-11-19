import { createI18n } from 'vue-i18n'
import en from './lang/en/en.json'
import esp from './lang/esp/esp.json'
import fr from './lang/fr/fr.json'

const i18n = createI18n({
    legace: 'false',
    locale: localStorage.getItem('lang') || 'en',
    fallbackLocale: 'en',
    messages: {
        en,
        esp,
        fr
    }
})

export default i18n
