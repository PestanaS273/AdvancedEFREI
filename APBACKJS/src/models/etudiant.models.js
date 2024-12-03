module.exports = (sequelize, DataTypes) => {
    const Etudiant = sequelize.define('Etudiant', {
      id: { 
        type: DataTypes.BIGINT, 
        primaryKey: true 
    },
      etat: DataTypes.BOOLEAN,
      num_etudiant: DataTypes.BIGINT,
    });
  
    Etudiant.associate = (models) => {
      Etudiant.belongsTo(models.Utilisateur, { foreignKey: 'id', as: 'utilisateur' });
      Etudiant.belongsToMany(models.Cours, {
        through: 'EtudiantCours',
        foreignKey: 'etudiant_id',
        otherKey: 'cours_id',
      });
    };
  
    return Etudiant;
  };