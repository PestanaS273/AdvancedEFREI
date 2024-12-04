import { createI18n } from 'vue-i18n'
import en from './lang/en/en.json'
import esp from './lang/esp/esp.json'
import fr from './lang/fr/fr.json'
import jap from './lang/jap/jap.json'

const i18n = createI18n({
    legace: 'false',
    locale: localStorage.getItem('lang') || 'en',
    fallbackLocale: 'en',
    messages: {
        en,
        esp,
        fr,
        jap
    }
})

export default i18n
