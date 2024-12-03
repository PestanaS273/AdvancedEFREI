import axios from 'axios';
import { jwtDecode } from 'jwt-decode';




class ConnexionService {
    constructor() {
        this.host = "http://localhost:8095";
        this.jwt = null;
        this.email = null;
        this.roles = [];
    }

    // Enregistrer le token JWT dans le localStorage et le parser
    saveToken(jwt) {
        if (jwt) {
            localStorage.setItem('token', jwt);
            this.jwt = jwt;
            this.parseJWT();
        }
    }

    // Appeler l'API pour inscrire un utilisateur patient
    inscrire(data) {
        return axios.post(`${this.host}/inscrire`, data);
    }

    // Appeler l'API pour inscrire un utilisateur médecin
    inscrire2(data) {
        return axios.post(`${this.host}/inscrire`, data);
    }

    // Appeler l'API pour se connecter
    loginAny(data) {
        return axios.post(`${this.host}/login`, data, { observe: 'response' });
    }

    // Décoder le JWT pour récupérer les informations utilisateur
    parseJWT() {
        if (this.jwt) {
            try {
                const decodedToken = jwtDecode(this.jwt);
                this.email = decodedToken.sub;
                this.roles = decodedToken.roles || [];
            } catch (error) {
                console.error('Error decoding JWT:', error);
            }
        }
    }

    // Vérifier si l'utilisateur est un professeur
    isProfesseur() {
        return this.roles.includes('Prof');
    }

    // Vérifier si l'utilisateur est un etudiant
    isEtudiant() {
        return this.roles.includes('student');
    }

    // Vérifier si l'utilisateur est un administrateur
    isAdmin() {
        return this.roles.includes('admin');
    }

    // Vérifier si l'utilisateur est authentifié
    isAuthenticated() {
        return this.roles.length > 0;
    }

    // Charger le token JWT depuis le localStorage
    loadToken() {
        this.jwt = localStorage.getItem('token');
        if (this.jwt) {
            this.parseJWT();
        }
    }

    // Déconnexion : supprimer le token et réinitialiser les paramètres
    logout() {
        localStorage.removeItem('token');
        this.initParams();
    }

    // Réinitialiser les paramètres utilisateur
    initParams() {
        this.jwt = null;
        this.email = null;
        this.roles = [];
    }
}

export default new ConnexionService();
