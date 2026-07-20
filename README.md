# Declinable Death Messages

<details>
<summary>🇷🇺 Русское описание</summary>

## Склоняемые сообщения о смерти

Клиентский Fabric-мод, позволяющий языкам с падежами (русский, украинский, польский, чешский, словацкий и другие) изменять форму имён убийц в сообщениях о смерти Minecraft.

Мод не изменяет существующие файлы переводов Minecraft. Вместо этого он добавляет поддержку дополнительных ключей перевода, которые могут быть предоставлены самим модом или наборами ресурсов.

Мод работает полностью на стороне клиента — сервер не требует установки мода.

## Требования

- Minecraft 26.1.2
- Fabric Loader 0.19.3+
- Fabric API
- Cloth Config (для экрана настроек)
- Mod Menu (необязательно, для открытия настроек через меню модов)

## Пример

Без мода:

```
Player был убит Паук
Player был убит Боб
Player был обречён на падение благодаря Паук
Player был сожжён дотла, пока боролся с Steve
```

С набором ресурсов:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку",
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s",
  "death.attack.onFire.player.by.Steve": "Steve-ом"
}
```

Результат:

```
Player был убит пауком
Player был убит Бобом
Player был обречён на падение благодаря пауку
Player был сожжён дотла, пока боролся со Steve-ом
```

## Возможности

- Поддержка ванильных и модовых сущностей.
- Поддержка переименованных мобов.
- Поддержка имён игроков.
- Поддержка всех сообщений с ключом `death.*`.
- Использование дополнительных ключей перевода через ресурс-паки.
- Полностью клиентская работа.
- Настройки через Cloth Config.
- Возможность отключить склонение имён игроков.
- Возможность отключить склонение сущностей.
- Возможность полностью заменять сообщения о смерти через ключи `.message`.
- Возврат оригинального имени, если склонение не найдено.
- Встроенный русский набор склонений для ванильных сущностей, некоторых имён и некоторых модовых сущностей.
- Поддержка оригинального сообщения во всплывающей подсказке.

## Формат ключей перевода

Мод поддерживает несколько типов ключей.

### Склонение имени убийцы

Формат:

```
death.<message>.by.<name>
```

Пример:

```json
{
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом"
}
```

Используется для:

- имён игроков;
- переименованных мобов.

---

### Склонение сущностей

Формат:

```
death.<message>.by.<namespace>.<entity>
```

или:

```
death.<message>.by.<entity>
```

Пример:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.spider": "пауком"
}
```

Поддерживаются ванильные и модовые сущности.

---

### Полная замена сообщения

Формат:

```
death.<message>.by.<killer>.message
```

Пример:

```json
{
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s"
}
```

Этот ключ заменяет всё сообщение, а не только имя убийцы.

---

## Оригинальное сообщение при наведении

Если включить настройку **«Показывать оригинальное сообщение»**, мод добавит оригинальное сообщение Minecraft во всплывающую подсказку изменённых сообщений.

Для работы необходимо включить расширенные подсказки Minecraft.

Пример:

```
Player был убит пауком
```

При наведении:

```
Оригинальное сообщение:
Player был убит Паук
```

## Настройки

### Склонение имён

Включает изменение формы имён игроков и переименованных сущностей.

### Склонение сущностей

Включает изменение формы названий непереименованных сущностей.

### Изменение сообщений о смерти

Позволяет использовать ключи:

```
death.<message>.by.<killer>.message
```

для полной адаптации сообщений под грамматику языка.

### Показывать оригинальное сообщение

Добавляет оригинальное сообщение Minecraft в подсказку при наведении.

## Установка

1. Установите Fabric Loader.
2. Установите Fabric API.
3. Установите Cloth Config.
4. Поместите мод в папку `mods`.
5. При необходимости установите ресурс-пак со склонениями.

По умолчанию мод использует встроенные русские склонения. Их можно отключить в настройках.

Если ваш язык не поддерживается модом, создайте ресурс-пак с необходимыми ключами перевода.

## Создание собственного набора склонений

Вы можете создать файл перевода, например:

```
assets/minecraft/lang/ru_ru.json
```

или отдельный ресурс-пак:

```
assets/<namespace>/lang/<language>.json
```

и добавить собственные ключи:

```json
{
  "death.attack.mob.by.minecraft.zombie": "зомби",
  "death.attack.player.by.Steve": "Стивом"
}
```

## Совместимость с ресурс-паками

Ресурс-паки могут добавлять собственные ключи склонений.

Будьте осторожны с неизвестными ресурс-паками: они могут полностью изменять сообщения о смерти через ключи `.message`.

## Лицензия

CC0-1.0

</details>


A client-side Fabric mod that allows languages with grammatical cases (such as Russian, Ukrainian, Polish, Czech, Slovak and others) to adapt killer names in Minecraft death messages.

The mod does not modify existing Minecraft translations. Instead, it adds support for additional translation keys provided by the mod itself or by resource packs.

The mod works entirely on the client side. The server does not need to install the mod.

## Requirements

- Minecraft 26.1.2
- Fabric Loader 0.19.3+
- Fabric API
- Cloth Config (required for the configuration screen)
- Mod Menu (optional, for accessing the configuration screen)

## Example

Without the mod:

```
Player was killed by Spider
Player was killed by Bob
Player fell from a high place because of Spider
Player was burned to death while fighting Steve
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "spider",
  "death.attack.mob.by.Bob": "Bob",
  "death.fell.assist.by.minecraft.spider": "spider",
  "death.attack.onFire.player.by.Steve.message": "%1$s was burned to death while fighting %2$s",
  "death.attack.onFire.player.by.Steve": "Steve"
}
```

Death messages become:

```
Player was killed by spider
Player was killed by Bob
Player fell from a high place because of spider
Player was burned to death while fighting Steve
```

## Features

- Supports vanilla and modded entities.
- Supports renamed mobs.
- Supports player names.
- Supports all `death.*` messages.
- Uses additional translation keys from resource packs.
- Works entirely on the client.
- Configurable through Cloth Config.
- Ability to disable name declension.
- Ability to disable entity declension.
- Full death message replacement through `.message` keys.
- Falls back to the original name if no declension exists.
- Built-in Russian declension entries.
- Original message hover support.

## Translation key format

### Killer name

```
death.<message>.by.<name>
```

Example:

```json
{
  "death.attack.player.by.Steve": "Steve"
}
```

Used for player names and renamed entities.

---

### Entity ID

```
death.<message>.by.<namespace>.<entity>
```

Example:

```json
{
  "death.attack.mob.by.minecraft.spider": "spider"
}
```

---

### Full message replacement

```
death.<message>.by.<killer>.message
```

Example:

```json
{
  "death.attack.onFire.player.by.Steve.message": "%1$s was burned to death while fighting %2$s"
}
```

## Original message hover

When enabled, the mod adds the original Minecraft death message to the hover tooltip of modified messages.

Minecraft Advanced Tooltips must be enabled.

Example:

```
Player was killed by spider
```

Hover:

```
Original message:
Player was killed by Spider
```

## Configuration

Available options:

- **Decline Names** — enables declension for player names and renamed entities.
- **Decline Entities** — enables declension for unnamed entities.
- **Grammar Adaptation for Death Messages** — enables full message replacement using `.message` keys.
- **Show Original Message** — displays the original Minecraft message in the hover tooltip.

## Installation

1. Install Fabric Loader.
2. Install Fabric API.
3. Install Cloth Config.
4. Put the mod into your `mods` folder.
5. Optionally install a resource pack with declension keys.

The mod includes Russian declension entries by default. They can be disabled in the configuration.

If your language is not supported, create a resource pack containing the required translation keys.

## License

Licensed under CC0-1.0.