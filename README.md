# Declinable Death Messages
<details>
<summary>🇷🇺 Русское описание</summary>

### Склоняемые сообщения о смерти

Клиентский Fabric-мод, позволяющий языкам со склонениями (русский, украинский, польский, чешский, словацкий и т.д.) изменять форму имена убийц в сообщениях о смерти Minecraft.

## Пример

Без мода:

```
Player был убит Паук
Player был убит Steve
Player был убит Боб
Player был обречён на падение благодаря Паук
```

В набор ресурсов:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

Сообщения станут:

```
Player был убит пауком
Player был убит Steve-ом
Player был убит Бобом
Player был обречён на падение благодаря пауку
```

## Возможности

- Поддержка как ванильных, так и модовых сущностей.
- Поддержка переименованных мобов.
- Поддержка имён игроков.
- Поддержка всех сообщений с ключом `death.*`.
- Использование ключей перевода из наборов ресурсов.
- Работа исключительно на стороне клиента.
- Возврат оригинального имени, если склонение не указано.
- Встроенные русские склонения для ванильных сущностей.

## Ключи перевода

Примеры:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

## Установка

Установите мод на клиент. Если ваш язык не поддерживается модом, установите дополнительный набор ресурсов с необходимыми ключами перевода.

## Лицензия

Под лицензией CC0-1.0

</details>

A client-side Fabric mod that allows languages that use grammatical cases (such as Russian, Ukrainian, Polish, Czech, Slovak and others) to inflect killer names in Minecraft death messages.

## Example (Russian)

Without the mod:

```
Player был убит Паук
Player был убит Steve
Player был убит Боб
Player был обречён на падение благодаря Паук
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

Death messages become:

```
Player был убит пауком
Player был убит Steve-ом
Player был убит Бобом
Player был обречён на падение благодаря пауку
```

## Features

- Supports vanilla and modded entities.
- Supports renamed mobs.
- Supports player names.
- Supports all `death.*` messages.
- Uses resource pack translation keys.
- Works entirely on the client.
- Falls back to the original name if no declension is provided.
- Built-in Russian declension entries for vanilla entities.

## Translation keys

Examples (Russian):

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

## Installation

Install the mod on the client. If your language isn't supported out of the box, install a resource pack containing the required translation keys.

## License

Licensed under CC0-1.0.