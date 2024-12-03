module.exports = (sequelize, DataTypes) => {
    const Admin = sequelize.define('Admin', {
      id: { type: DataTypes.BIGINT, primaryKey: true },
    });
  
    Admin.associate = (models) => {
      Admin.belongsTo(models.Utilisateur, { foreignKey: 'id', as: 'utilisateur' });
      Admin.belongsToMany(models.Cours, {
        through: 'AdminCours',
        foreignKey: 'admin_id',
        otherKey: 'cours_id',
      });
    };
  
    return Admin;
  };
  