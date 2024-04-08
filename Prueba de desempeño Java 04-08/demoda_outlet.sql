SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente`  (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `apellido` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_cliente`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra`  (
  `id_compra` int NOT NULL,
  `fk_cliente` int NOT NULL,
  `fk_producto` int NOT NULL,
  `fecha_compra` varchar NULL DEFAULT NULL,
  `cantidad` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_compra`) USING BTREE,
  INDEX `fk_cliente`(`fk_cliente` ASC) USING BTREE,
  INDEX `fk_producto`(`fk_producto` ASC) USING BTREE,
  CONSTRAINT `fk_cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_producto` FOREIGN KEY (`fk_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto`  (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `precio` decimal(10, 2) NULL DEFAULT NULL,
  `fk_tienda` int NOT NULL,
  PRIMARY KEY (`id_producto`) USING BTREE,
  INDEX `fk_tienda`(`fk_tienda` ASC) USING BTREE,
  CONSTRAINT `fk_tienda` FOREIGN KEY (`fk_tienda`) REFERENCES `tienda` (`id_tienda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `tienda`;
CREATE TABLE `tienda`  (
  `id_tienda` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ubicacion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_tienda`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

INSERT INTO `tienda` VALUES (1, 'Totto', 'Local 204');

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE producto
ADD COLUMN stock INT;
