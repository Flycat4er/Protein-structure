# Protein Structure — Каркас на Compose Multiplatform (CMP)

Кроссплатформенное приложение каталога пространственных структур белков на основе [RCSB Protein Data Bank API](https://search.rcsb.org/rcsbsearch/v2/query).

Разработано по критериям воркшопа **Веха В1 «Каркас приложения на CMP»** на базе архитектурного шаблона `pokedex-cmp`.

---

## 🚀 Быстрый запуск

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
*Вывод: `values-en: 0 расхождений, ключей 26` (код возврата 0)*

### 4. Запуск юнит-тестов
```bash
./gradlew :shared:jvmTest
```

---

## 📋 Выполнение критериев приёмки (Веха В1)

| Критерий | Реализация в проекте |
|---|---|
| **1. Навигация — Navigation 3** | `sealed interface Screen` с `data object List : Screen` и `data class Detail(val id: String) : Screen` в [`Screen.kt`](shared/src/commonMain/kotlin/org/example/proteinstructure/Screen.kt). Маршруты типизированы, никаких строковых путей. |
| **2. Мультиплатформенная сборка навигации** | Использована официальная мультиплатформенная сборка JetBrains: `org.jetbrains.androidx.navigation3:navigation3-ui:1.1.1` (runtime `androidx.navigation3:navigation3-runtime:1.1.1`). |
| **3. Переход «возврат выглядит как возврат»** | В [`AppNavDisplay.kt`](shared/src/commonMain/kotlin/org/example/proteinstructure/ui/navigation/AppNavDisplay.kt) заданы `transitionSpec = { slide(SlideDirection.Start) }`, `popTransitionSpec = { slide(SlideDirection.End) }` и `predictivePopTransitionSpec`. При возврате экран выезжает слева направо. |
| **4. Подписи в `composeResources`** | Строки вынесены в `values/strings.xml` (русский) и `values-en/strings.xml` (английский). Паритет проверен скриптом [`tools/check-strings.py`](tools/check-strings.py). |
| **5. Тема — токены `MaterialTheme`** | Все поверхности используют `MaterialTheme.colorScheme.surfaceContainer`, `surface`, `onSurface` и т.д. В [`SemanticColors.kt`](shared/src/commonMain/kotlin/org/example/proteinstructure/ui/SemanticColors.kt) вынесены цвета данных (метод исследования, качество разрешения), которые не меняются от темы. |
| **6. Экран рисуется из состояния** | Stateless Composable (`ProteinListScreen`, `ProteinDetailScreen`) принимают иммутабельное состояние и колбэк намерения (`onIntent`), без прямой передачи ViewModel. |
| **7. Отчёт компилятора включён** | В `shared/build.gradle.kts` настроены `reportsDestination` и `metricsDestination` в папку `compose_compiler`. Экраны выводятся как `restartable skippable`. |
| **8. 20+ предметных моков под RCSB PDB** | В [`MockProteins.kt`](shared/src/commonMain/kotlin/org/example/proteinstructure/data/MockProteins.kt) вручную описаны 22 реальные структуры PDB (`4HHB`, `6LU7`, `1EMA`, `1TIM`, `3NIR` и др.) с полями, идентичными API В2. |

---

## 🏛 Структура проекта

```
Protein-structure/
├── gradle/
│   ├── libs.versions.toml             # Каталог версий зависимостей (CMP, Nav3, M3)
│   └── wrapper/                       # Gradle wrapper 9.1.0
├── desktopApp/                        # Точка входа для Desktop JVM
│   └── src/main/kotlin/.../main.kt
├── shared/                            # Общий модуль (Kotlin Multiplatform + Compose)
│   ├── src/commonMain/
│   │   ├── composeResources/          # Ресурсы (локали values/ru, values-en, иконки)
│   │   └── kotlin/org/example/proteinstructure/
│   │       ├── Screen.kt              # Маршруты Navigation 3
│   │       ├── App.kt                 # Главный компонент, сессия темы, AppScaffold
│   │       ├── domain/                # Protein, ProteinMethod, ProteinRepository
│   │       ├── data/                  # MockProteins (22 структуры), ProteinRepositoryImpl
│   │       ├── list/                  # MVI: State, Intent, ViewModel, Factory
│   │       ├── detail/                # MVI: State, Intent, ViewModel, Factory
│   │       └── ui/                    # Тема, токены, компоненты, экраны, навигация
│   └── src/commonTest/                # Юнит-тесты репозитория и навигатора
└── tools/
    └── check-strings.py               # Скрипт проверки паритета ключей локализации
```

---

## 🧪 Что не сделано (согласно правилам Вехи В1):
- Нет сети (запросы к API пойдут в Вехе В2).
- Нет локального хранилища на диске (тема и язык живут в сессии памяти).
- Нет адаптива под ширину окна и мобилку (один таргет — Desktop JVM).
- Не переведены научные данные каталога (локализован только пользовательский интерфейс).
