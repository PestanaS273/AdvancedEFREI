import { createStore } from 'vuex';
import user from './user.store';
import form from './form.store';
import collectionForms from './collectionForms.store';

export default createStore({
    modules: {
        user,
        form,
        collectionForms,
    },
});