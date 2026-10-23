# Protein-structure

## Быстрый запуск

### 1. Запуск десктопного приложения
```bash
./gradlew :desktopApp:run
```
*(на Windows: `.\gradlew.bat :desktopApp:run`)*

### 2. Запуск с английской локализацией (English)
```bash
./gradlew :desktopApp:run -Plocale=en
```

### 3. Запуск веб-приложения (Wasm)
```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
```

### 4. Сборка Android-приложения
```bash
./gradlew :androidApp:assembleDebug
```

