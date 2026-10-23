# Protein-structure

## Быстрый запуск

### 1. Запуск десктопного приложения
```bash
./gradlew :desktopApp:run
```
*(на Windows: `.\gradlew.bat :desktopApp:run`)*

### 2. Запуск со второй локалью (English)
```bash
./gradlew :desktopApp:run -Plocale=en
```

### 3. Проверка паритета строк локализации
```bash
python tools/check-strings.py
```

### 4. Запуск тестов
```bash
./gradlew :shared:jvmTest
```
