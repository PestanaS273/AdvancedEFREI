module.exports = (sequelize, DataTypes) => {
    const Question = sequelize.define('Question', {
      id: { type: DataTypes.BIGINT, primaryKey: true, autoIncrement: true },
      question: DataTypes.STRING,
    });
  
    Question.associate = (models) => {
      Question.belongsTo(models.Forme, { foreignKey: 'id', as: 'forme' });
    };
  
    return Question;
  };
  