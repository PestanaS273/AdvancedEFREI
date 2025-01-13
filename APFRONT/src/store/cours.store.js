const state = {
    cours: JSON.parse(localStorage.getItem('cours')) || null,
    coursId: localStorage.getItem('coursId') || null,
};

const mutations = {
    setCours(state, cours) {
        state.cours = cours;
        localStorage.setItem('cours', JSON.stringify(cours));
        console.log('cours:', cours);
    },
    exitCours(state) {
        state.cours = null;
        localStorage.removeItem('cours');
    },
    setCoursId(state, coursId) {
        state.coursId = coursId;
        localStorage.setItem('coursId', coursId);
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