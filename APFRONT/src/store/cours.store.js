const state = {
    cours: null,
    coursId: null,
};

const mutations = {
    setCours(state, cours) {
        state.cours = cours;
        console.log('cours:', cours);
    },
    exitCours(state) {
        state.cours = null;
    },
    setCoursId(state, coursId) {
        state.coursId = coursId;
    }
};

const actions = {
    setCours({ commit }, cours) {
        commit('setCours', cours);
    },
    exitCours({ commit }) {
        commit('exitCours');
    },
    setCoursId({ commit }, coursId) {
        commit('setCoursId', coursId);
    }
};

const getters = {
    getCours: state => state.cours,
    getCoursId: state => state.coursId
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};