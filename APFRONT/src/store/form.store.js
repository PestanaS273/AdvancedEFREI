const state = {
    form: JSON.parse(localStorage.getItem('form')) || null,
};

const mutations = {
    setForm(state, form) {
        state.form = form;
        localStorage.setItem('form', JSON.stringify(form));
        console.log('Form:', form);
    },
    exitForm(state) {
        state.form = null;
        localStorage.removeItem('form');
    },
};

const actions = {
    setForm({ commit }, form) {
        commit('setForm', form);
    },
    exitForm({ commit }) {
        commit('exitForm');
    },
};

const getters = {
    getForm: state => state.form,
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};