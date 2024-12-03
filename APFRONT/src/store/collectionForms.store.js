const state = {
    collectionForms: null,
};

const mutations = {
    setCollectionForms(state, collectionForms) {
        state.collectionForms = collectionForms;
        console.log('Collection Forms:', collectionForms);
    },
    exitCollectionForms(state) {
        state.collectionForms = null;
    },
};

const actions = {
    setCollectionForms({ commit }, collectionForms) {
        commit('setCollectionForms', collectionForms);
    },
    exitCollectionForms({ commit }) {
        commit('exitCollectionForms');
    },
};

const getters = {
    getCollectionForms: state => state.collectionForms,
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
};