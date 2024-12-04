const state = {
    user: JSON.parse(localStorage.getItem('user')) || null,
    isAuthenticated: !!localStorage.getItem('user'),
};

const mutations = {
    setUser(state, user) {
        state.user = user;
        console.log('User:', user);
        state.isAuthenticated = true;
        localStorage.setItem('user', JSON.stringify(user));
    },
    logout(state) {
        state.user = null;
        console.log('User logged out');
        state.isAuthenticated = false;
        localStorage.removeItem('user');

    },
};

const actions = {
    login({ commit }, user) {
        commit('setUser', user);
    },
    logout({ commit }) {
        commit('logout');
    },
    setUser({ commit }, user) {
        commit('setUser', user);
    }
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