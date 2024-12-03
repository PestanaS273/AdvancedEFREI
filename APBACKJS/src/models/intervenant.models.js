module.exports = (sequelize, DataTypes) => {
    const Intervenant = sequelize.define('Intervenant', {
      id: { type: DataTypes.BIGINT, primaryKey: true },
      description: DataTypes.STRING,
      specialiste: DataTypes.STRING,
    });
  
    Intervenant.associate = (models) => {
      Intervenant.belongsTo(models.Utilisateur, { foreignKey: 'id', as: 'utilisateur' });
      Intervenant.belongsToMany(models.Cours, {
        through: 'IntervenantCours',
        foreignKey: 'intervenant_id',
        otherKey: 'cours_id',
      });
    };
  
    return Intervenant;
  };
  