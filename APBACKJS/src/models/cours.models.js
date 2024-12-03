module.exports = (sequelize, DataTypes) => {
    const Cours = sequelize.define('Cours', {
      id: { type: DataTypes.BIGINT, primaryKey: true, autoIncrement: true },
      nom_cours: { type: DataTypes.STRING, unique: true, allowNull: false },
    });
  
    Cours.associate = (models) => {
      Cours.belongsToMany(models.Etudiant, {
        through: 'EtudiantCours',
        foreignKey: 'cours_id',
        otherKey: 'etudiant_id',
      });
      Cours.belongsToMany(models.Intervenant, {
        through: 'IntervenantCours',
        foreignKey: 'cours_id',
        otherKey: 'intervenant_id',
      });
      Cours.belongsToMany(models.Admin, {
        through: 'AdminCours',
        foreignKey: 'cours_id',
        otherKey: 'admin_id',
      });
    };
  
    return Cours;
  };
  