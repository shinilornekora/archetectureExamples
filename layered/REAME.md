Имеем четыре слоя.
- Presentation хранит в себе CLI-логику
- Application хранит в себе способ обращения к сервисам
- Domain хранит в себе бизнес-логику
- Infrastructure содержит реализацию хранения данных

В рамках реализации слоистой архитектуры делаем такие операции
• Добавление новых продуктов в инвентарь при поступлении
• Списание продуктов при использовании для приготовления блюд
• Списание просроченных продуктов
• Проведение инвентаризации и корректировка запасов
• Генерация отчетов о текущих запасах
• Отслеживание продуктов с критическим уровнем запасов
