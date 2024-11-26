const state = {
    user: null,
    isAuthenticated: false,
}

const mutation = {
    setUser(state, user) {
        state.user = user;
        state.isAuthenticated = true;
    },
    logout(state) {
        state.user = null;
        state.isAuthenticated = !!user;
    },
};

const actions = {
    login ({ commit }, user) {
        commit('setUser', user);
    },
    logout ({ commit }) {
        commit('logout');
    },
};

const getters = {
    isAuthenticated: state => state.isAuthenticated, 
    getUser: (state) => state.user,
}

export default {
    state, 
    mutation,
    actions,
    getters,
}

