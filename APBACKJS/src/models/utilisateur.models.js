module.exports = (sequelize, DataTypes) => {
    const Utilisateur = sequelize.define("utilisateur", {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        nom: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        prenom: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        date_naissance: {
            type: DataTypes.DATE,
            allowNull: false,
        },
        statut: {
            type: DataTypes.BOOLEAN,
            allowNull: false,
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
        },
        image_profil: {
            type: DataTypes.STRING,
            allowNull: true,
        },
        num_tel: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        role: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    });

    Utilisateur.associate = (models) => {
        Utilisateur.belongsTo(models.Role, {
            foreignKey: {
                allowNull: false,
            },
        });
    };

    Utilisateur.associate = (models) => {
        Utilisateur.hasOne(models.Etudiant, { foreignKey: 'id', as: 'etudiant' });
        Utilisateur.hasOne(models.Admin, { foreignKey: 'id', as: 'admin' });
        Utilisateur.hasOne(models.Intervenant, { foreignKey: 'id', as: 'intervenant' });
      };

    return Utilisateur;
}