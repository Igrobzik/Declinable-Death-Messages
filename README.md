# Declinable Death Messages
<details>
<summary>🇷🇺 Русское описание</summary>

### Склоняемые сообщения о смерти

Клиентский Fabric-мод, позволяющий языкам с грамматическими падежами (русский, украинский, польский, чешский, словацкий и т.д.) изменять форму имён убийц в сообщениях о смерти Minecraft.

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

Мод не изменяет существующие файлы переводов. Вместо этого он использует дополнительные ключи перевода из встроенных переводов или наборов ресурсов.

## Лицензия

Под лицензией CC0-1.0

</details>

A client-side Fabric mod that allows languages with grammatical cases (such as Russian, Ukrainian, Polish, Czech, Slovak and others) to inflect killer names in Minecraft death messages.

## Examples

<details>
<summary>Russian</summary>

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

</details>

<details>
<summary>Serbian</summary>

Example only. Not included by default.

Without the mod:

```
Player je ubijen od strane Pauk
Player je ubijen od strane Steve
Player je ubijen od strane Bob
Player je usahnuo tokom borbe sa Steve
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "pauka",
  "death.attack.player.by.Steve": "Stevea",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.wither.player.by.Steve": "Steveom"
}
```

Death messages become:

```
Player je ubijen od strane pauka
Player je ubijen od strane Stevea
Player je ubijen od strane Boba
Player je usahnuo tokom borbe sa Steveom
```

</details>

<details>
<summary>Polish</summary>

Without the mod:

```
Gracz Player został zabity przez: Pająk
Gracz Player został zabity przez: Steve
Gracz Player został zabity przez: Bob
Gracz Player obumarł podczas walki z Steve
```

With a resource pack:

```json
{
  "death.attack.mob": "Gracz %1$s został zabity przez %2$s",
  "death.attack.mob.by.minecraft.spider": "pająka",
  "death.attack.player": "Gracz %1$s został zabity przez %2$s",
  "death.attack.player.by.Steve": "Steve'a",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.wither.player.by.minecraft.spider": "Stevem"
}
```

Death messages become:

```
Gracz Player został zabity przez pająka
Gracz Player został zabity przez Steve'a
Gracz Player został zabity przez Boba
Gracz Player obumarł podczas walki z Stevem
```

</details>

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

Examples:

<details>
<summary>Russian</summary>

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

</details>

<details>
<summary>Serbian</summary>

Example only. Not included by default.

```json
{
  "death.attack.mob.by.minecraft.spider": "pauka",
  "death.attack.player.by.Steve": "Stevea",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.wither.player.by.Steve": "Steveom"
}
```

</details>
<summary>Serbian</summary>

Example only. Not included by default.

```json
{
  "death.attack.mob": "Gracz %1$s został zabity przez %2$s",
  "death.attack.mob.by.minecraft.spider": "pająka",
  "death.attack.player": "Gracz %1$s został zabity przez %2$s",
  "death.attack.player.by.Steve": "Steve'a",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.wither.player.by.minecraft.spider": "Stevem"
}
```

</details>

## Installation

Install the mod on the client. If your language isn't supported out of the box, install a resource pack containing the required translation keys.

The mod does not modify language files directly. Instead, it looks for additional translation keys in resource packs.

## License

Licensed under CC0-1.0.