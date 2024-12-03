module.exports = (sequelize, DataTypes) => {
    const Role = sequelize.define("role", {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        role: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    });

    Role.associate = (models) => {
        Role.hasMany(models.Utilisateur, {
            foreignKey: {
                allowNull: false,
            },
        });
    };

    return Role;
}