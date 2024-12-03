module.exports = (sequelize, DataTypes) => {
    const Forme = sequelize.define('Forme', {
      id: { type: DataTypes.BIGINT, primaryKey: true, autoIncrement: true },
      question_id: DataTypes.BIGINT,
      reponse_id: DataTypes.BIGINT,
    });
  
    Forme.associate = (models) => {
      Forme.hasMany(models.Question, { foreignKey: 'question_id', as: 'questions' });
    };
  
    return Forme;
  };
  