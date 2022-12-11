SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE TABLE IF NOT EXISTS `TestDataBase` (
  `position` int(11) NOT NULL AUTO_INCREMENT,
  `id` text NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`position`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

INSERT INTO `TestDataBase` (`position`, `id`, `name`, `description`) VALUES
(1, '@Michael', 'Michael', 'MichaelMichaelMichaelMichael'),
(2, '@Artur', 'Artur', 'ArturArturArturArturArtur'),
(3, '@Denis', 'Denis', 'DenisDenisDenisDenisDenisDenis'),
(4, '@Woolf', 'Woolf', 'WoolfWoolfWoolfWoolfWoolfWoolf'),
(5, '@Monica', 'Monica', 'MonicaMonicaMonicaMonicaMonica');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
