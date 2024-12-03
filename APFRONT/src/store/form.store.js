const state = {
    form: null,
};

const mutations = {
    setForm(state, form) {
        state.form = form;
        console.log('Form:', form);
    },
    exitForm(state) {
        state.form = null;
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