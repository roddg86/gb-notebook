#### ДЗ Рефакторинг и\или оптимизация проекта предыдущего дз с учетом теоретических основ SOLID’а

Так как в классе FileOperation saveAll() выполняет тоже что и метод write() в UserRepository, удалим saveAll(),

и перенесем readAll() в UserRepository 

FileOperation удалим из-за задвоения функционала с UserRepository.
