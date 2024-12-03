const config = require("../config/config");
const Sequelize = require("sequelize");

const sequelize = new Sequelize(
  config.db.database,
  config.db.user,
  config.db.password,
  {
    dialect: config.db.dialect,
    host: config.db.host,
  }
);


const db = {};
db.sequelizeMain = sequelize;
db.Sequelize = Sequelize;

// db.Admin = require("./admin.models")(sequelize, Sequelize.DataTypes);
// db.Cours = require("./cours.models")(sequelize, Sequelize.DataTypes);
db.Etudiant = require("./etudiant.models")(sequelize, Sequelize.DataTypes);
// db.Forme = require("./forme.models")(sequelize, Sequelize.DataTypes);
// db.Intervenant = require("./intervenant.models")(sequelize, Sequelize.DataTypes);
// db.Question = require("./question.models")(sequelize, Sequelize.DataTypes);
// db.Response = require("./response.models")(sequelize, Sequelize.DataTypes);
db.Role = require("./role.models")(sequelize, Sequelize);
db.Utilisateur = require("./utilisateur.models")(sequelize, Sequelize.DataTypes);

Object.keys(db).forEach((modelName) => {
    if ("associate" in db[modelName]) {
      db[modelName].associate(db);
    }
});

module.exports = db;

sequelize.authenticate()
  .then(() => {
    console.log('Connection with main database stablished correctly.');
  })
  .catch(err => {
    console.error('Error reaching the database:', err);
  });


