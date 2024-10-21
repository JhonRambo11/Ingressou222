Alterados alguns campos do banco de dados

-- Banco de Dados para Sistema Ingressou
-- Este sistema armazena dados relacionados a eventos, usuários, ingressos comprados, e pedidos.
-- Principais funcionalidades incluem: cadastro de eventos, controle de ingressos por tipo, gerenciamento de usuários e histórico de pedidos.

-- Configurações iniciais para evitar erros de chave estrangeira e modo restrito
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Criação do esquema (database)
CREATE SCHEMA IF NOT EXISTS `Ingressou22` DEFAULT CHARACTER SET utf8mb3 ;
USE `Ingressou22` ;

-- Tabela `tbevento`: Armazena informações detalhadas sobre os eventos cadastrados no sistema.
-- A tabela contém dados como descrição, data, local, horário e capacidade.
-- Relacionada com `tbtipoingresso` e `tbingresso` para controle dos ingressos disponíveis e tipos de ingressos oferecidos para cada evento.
CREATE TABLE IF NOT EXISTS `tbevento` (
  `CDEVENTO` INT UNSIGNED NOT NULL AUTO_INCREMENT,        -- Chave Primária: Identificador único para cada evento.
  `DSDESCRICAO` VARCHAR(255) NOT NULL,                    -- Descrição completa do evento, até 255 caracteres.
  `DTEVENTO` DATE NOT NULL,                           -- Data do evento no formato AAAA-MM-DD.
  `NMEVENTO` VARCHAR(45) NOT NULL,                        -- Nome curto do evento, útil para identificação rápida.
  `DSLOCALEVENTO` VARCHAR(45) NOT NULL,                   -- Local onde o evento ocorre, até 45 caracteres.
  `NUFAIXAETARIA` INT NOT NULL,                           -- Faixa etária recomendada para o evento, utilizada para controle de público.
  `IMEVENTOURL` VARCHAR(255) NOT NULL,                    -- URL para imagem do evento.
  `HRABERTURAEVENTO` TIME NOT NULL,                       -- Horário de abertura, para planejamento logístico.
  `HRINICIO` TIME NOT NULL,                               -- Horário de início do evento.
  `NUCAPACIDADE` INT NOT NULL,                            -- Capacidade total de pessoas permitidas no evento.
  `NUQTDINGRESSOS` INT NOT NULL,                          -- Quantidade de ingressos disponíveis para venda.
  PRIMARY KEY (`CDEVENTO`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- Tabela `tbusuario`: Contém dados dos usuários que compram ingressos para os eventos.
-- Cada usuário é único e possui informações como nome, cidade, estado, telefone, idade e e-mail.
-- Campos `DSEMAIL` e `DOCUMENTO` são únicos.
CREATE TABLE IF NOT EXISTS `tbusuario` (
  `CDUSUARIO` INT UNSIGNED NOT NULL AUTO_INCREMENT,       -- Chave Primária: Identificador exclusivo para cada usuário.
  `NMUSUARIO` VARCHAR(255) NOT NULL,                      -- Nome completo do usuário, até 255 caracteres.
  `DSCIDADE` VARCHAR(100) NOT NULL,                       -- Cidade de residência.
  `DSESTADO` VARCHAR(2) NOT NULL,                         -- Estado, abreviado em duas letras (ex: SP, RJ).
  `NUTELEFONE` VARCHAR(20) NOT NULL,                      -- Número de telefone para contato.
  `NUIDADE` INT DEFAULT NULL,                             -- Idade do usuário, opcional.
  `DSEMAIL` VARCHAR(100) NOT NULL,                        -- E-mail do usuário, único para login e notificações.
  `DSSENHA` VARCHAR(255) NOT NULL,                        -- Senha armazenada Varchar de 255.
  `DOCUMENTO` VARCHAR(45) NOT NULL,                       -- Documento de identidade único (CPF ou RNE para estrangeiros).
  PRIMARY KEY (`CDUSUARIO`),
  UNIQUE INDEX `DSEMAIL` (`DSEMAIL`),                     -- Índice único para garantir e-mail único no sistema.
  UNIQUE INDEX `DOCUMENTO` (`DOCUMENTO`)                  -- Índice único para assegurar documento único no sistema.
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- Tabela `tbtipoingresso`: Armazena os tipos de ingressos disponíveis para cada evento.
-- Cada tipo de ingresso, como VIP ou Pista, tem um preço base e é associado a um evento específico.
CREATE TABLE IF NOT EXISTS `tbtipoingresso` (
  `CDTIPOINGRESSO` INT UNSIGNED NOT NULL AUTO_INCREMENT,  -- Chave Primária: Identificador único para o tipo de ingresso.
  `CDEVENTO` INT UNSIGNED NOT NULL,                       -- Chave estrangeira para identificar o evento relacionado.
  `NMTIPOINGRESSO` VARCHAR(45) NOT NULL,                  -- Nome do tipo de ingresso, descritivo (ex: VIP, Geral).
  `VLPRECOBASE` DECIMAL(16,2) NOT NULL,                   -- Preço base para o tipo de ingresso.
  `QTDDISPONIVEL` INT UNSIGNED NOT NULL DEFAULT 0,        -- Coluna para controlar a quantidade disponível para cada tipo de ingresso
  PRIMARY KEY (`CDTIPOINGRESSO`),
  INDEX `CDEVENTO` (`CDEVENTO`),                          -- Índice para consultas rápidas pelo evento.
  CONSTRAINT `tbtipoingresso_ibfk_1`
    FOREIGN KEY (`CDEVENTO`) REFERENCES `tbevento` (`CDEVENTO`)  -- Relação com a tabela `tbevento`.
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- Tabela `tbpedido`: Registra pedidos realizados por usuários para ingressos.
-- Os pedidos possuem um status e um valor total, e estão associados a um usuário.
CREATE TABLE IF NOT EXISTS `tbpedido` (
  `CDPEDIDO` INT UNSIGNED NOT NULL AUTO_INCREMENT,        -- Chave Primária: Identificador exclusivo para o pedido.
  `CDUSUARIO` INT UNSIGNED NOT NULL,                      -- Chave estrangeira para identificar o usuário associado ao pedido.
  `CDEVENTO` INT UNSIGNED NOT NULL,
  `DTPEDIDO` DATETIME NOT NULL,                           -- Data e hora do pedido.
  `DSSTATUS` VARCHAR(50) NOT NULL,                        -- Status do pedido, como Pendente ou Confirmado.
  `VLTOTALPEDIDO` DECIMAL(16,2) NOT NULL,                 -- Valor total do pedido, em moeda.
  PRIMARY KEY (`CDPEDIDO`),
  INDEX `CDUSUARIO` (`CDUSUARIO`),                        -- Índice para consultas rápidas por usuário.
  CONSTRAINT `tbpedido_ibfk_1`
    FOREIGN KEY (`CDUSUARIO`) REFERENCES `tbusuario` (`CDUSUARIO`),  -- Relação com a tabela `tbusuario`.
  CONSTRAINT `tbpedido_ibfk_2`
    FOREIGN KEY (`CDEVENTO`) REFERENCES `tbevento` (`CDEVENTO`)     -- Relação com `tbevento`.
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;

-- Tabela `tbingresso`: Contém informações sobre ingressos comprados pelos usuários.
-- Inclui quantidade comprada, data da compra e preço total, associando os ingressos a eventos e usuários.
CREATE TABLE IF NOT EXISTS `tbingresso` (
  `CDINGRESSO` INT UNSIGNED NOT NULL AUTO_INCREMENT,      -- Chave Primária: Identificador exclusivo para o ingresso.
  `CDUSUARIO` INT UNSIGNED NOT NULL,                      -- Chave estrangeira para identificar o comprador (usuário).
  `CDEVENTO` INT UNSIGNED NOT NULL,                       -- Chave estrangeira para identificar o evento relacionado.
  `CDTIPOINGRESSO` INT UNSIGNED NOT NULL,                 -- Chave estrangeira para identificar o tipo de ingresso comprado.
  `CDPEDIDO` INT UNSIGNED NOT NULL,                       -- Chave estrangeira para identificar o pedido ao qual o ingresso está associado.
  `NUINGRESSO` INT UNSIGNED NOT NULL,                     -- Número de cada ingresso,(os números são unicos apenas para cada evento)
  `DTHRCOMPRA` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,       -- Data e hora da compra.
  `QTDCOMPRA` INT,                               		  -- Quantidade de ingressos comprados em uma única transação.
  PRIMARY KEY (`CDINGRESSO`),
  INDEX `CDUSUARIO` (`CDUSUARIO`),                        -- Índice para facilitar consultas por usuário.
  INDEX `CDEVENTO` (`CDEVENTO`),                          -- Índice para facilitar consultas por evento.
  INDEX `CDTIPOINGRESSO` (`CDTIPOINGRESSO`),              -- Índice para facilitar consultas por tipo de ingresso.
  INDEX `CDPEDIDO` (`CDPEDIDO`),                          -- Índice para facilitar consultas por pedido.
  CONSTRAINT `tbingresso_ibfk_1`
    FOREIGN KEY (`CDUSUARIO`) REFERENCES `tbusuario` (`CDUSUARIO`),  -- Relação com `tbusuario`.
  CONSTRAINT `tbingresso_ibfk_2`
    FOREIGN KEY (`CDEVENTO`) REFERENCES `tbevento` (`CDEVENTO`),     -- Relação com `tbevento`.
  CONSTRAINT `tbingresso_ibfk_3`
    FOREIGN KEY (`CDTIPOINGRESSO`) REFERENCES `tbtipoingresso` (`CDTIPOINGRESSO`),  -- Relação com `tbtipoingresso`.
  CONSTRAINT `tbingresso_ibfk_4`
    FOREIGN KEY (`CDPEDIDO`) REFERENCES `tbpedido` (`CDPEDIDO`)   -- Relação com `tbpedido`.
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3;


-- Restaurando as configurações de chaves únicas e estrangeiras
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
