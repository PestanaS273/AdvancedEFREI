const state = {
    user: null,
    isAuthenticated: false,
};

const mutations = {
    setUser(state, user) {
        state.user = user;
        console.log('User:', user);
        state.isAuthenticated = true;
    },
    logout(state) {
        state.user = null;
        state.isAuthenticated = false;
    },
};

const actions = {
    login({ commit }, user) {
        commit('setUser', user);
    },
    logout({ commit }) {
        commit('logout');
    },
};

const getters = {
    isAuthenticated: state => state.isAuthenticated,
    getUser: state => state.user,
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};