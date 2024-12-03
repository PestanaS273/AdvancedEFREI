const express = require('express');
const router = express.Router();
const Utilisateur = require('../models/utilisateur.models');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const db = require('../models/index');

// User registration
router.post('/register', async (req, res) => {
    try {
        const { nom, prenom, date_naissance, email, password, num_tel } = req.body;

        // Verificar si el usuario ya existe
        const existingUser = await Utilisateur.findOne({ where: { email } });
        if (existingUser) {
            return res.status(400).json({ error: 'User already exists' });
        }

        // Hashear la contraseña
        const hashedPassword = await bcrypt.hash(password, 10);

        // Crear el usuario
        const user = await Utilisateur.create({
            nom,
            prenom,
            date_naissance,
            statut: true, // Activo por defecto
            email,
            num_tel,
            password: hashedPassword,
            role: 'etudiant', // Asignar rol automáticamente
        });

        // Asociar el usuario como estudiante
        await db.Etudiant.create({
            id: user.id, // Usar el ID del usuario recién creado
            etat: true, // Estado activo por defecto
            num_etudiant: Math.floor(100000 + Math.random() * 900000), // Generar un número de estudiante aleatorio
        });

        res.status(201).json({ message: 'User registered successfully as student', user });
    } catch (error) {
        console.error('Error during registration:', error);
        res.status(500).json({ error: 'Registration failed' });
    }
});

   
   // User login
router.post('/login', async (req, res) => {
    try {
        const { email, password } = req.body;
        const user = await User.findOne({ email });
        if (!user) {
            return res.status(401).json({ error: 'Authentication failed' });
        }
        const passwordMatch = await bcrypt.compare(password, user.password);
        if (!passwordMatch) {
            return res.status(401).json({ error: 'Authentication failed' });
        }
        const token = jwt.sign({ userId: user._id }, 'your-secret-key', {
            expiresIn: '1h',
        });
        res.status(200).json({ token });
    } catch (error) {
        res.status(500).json({ error: 'Login failed' });
    }
});

module.exports = router;