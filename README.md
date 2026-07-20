# Declinable Death Messages
<details>
<summary>🇷🇺 Русское описание</summary>

### Склоняемые сообщения о смерти

Клиентский Fabric-мод, позволяющий языкам с падежами (русский, украинский, польский, чешский, словацкий и т.д.) изменять форму имён убийц в сообщениях о смерти Minecraft.

Мод не изменяет существующие файлы переводов. Вместо этого он использует дополнительные ключи перевода из встроенных переводов или наборов ресурсов. Он лишь добавляет поддержку дополнительных ключей перевода, которые могут быть предоставлены самим модом (для встроенных языков) или наборами ресурсов.

## Пример

Без мода:

```
Player был убит Паук
Player был убит Боб
Player был обречён на падение благодаря Паук
Player был сожжён дотла, пока боролся с Steve
```

В наборе ресурсов:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку",
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s",
  "death.attack.onFire.player.by.Steve": "Steve-ом"
}
```

Сообщения станут:

```
Player был убит пауком
Player был убит Бобом
Player был обречён на падение благодаря пауку
Player был сожжён дотла, пока боролся со Steve-ом
```

## Возможности

- Поддержка как ванильных, так и модовых сущностей.
- Поддержка переименованных мобов.
- Поддержка имён игроков.
- Поддержка всех сообщений с ключом `death.*`.
- Использование ключей перевода из наборов ресурсов.
- Работа исключительно на стороне клиента.
- Изменение сообщения о смерти в соответствии с названием сущности или имени моба/игрока.
- Настройки через Cloth Config.
- Возможность отключить склонение имён игроков и сущностей через настройки мода.
- Возможность отключить измение сообщений через настройки мода.
- Возможность включить отображение исходного сообщения подсказкой через настройки мода.
- Возврат оригинального имени, если склонение или сообщение не указано.
- Встроенный набор русских склонений для ванильных сущностей, некоторых имён и некоторых модовых сущностей и сообщений о смерти (которые вы можете использовать как пример для перевода на ваш язык).

## Ключи перевода

Примеры:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку",
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s",
  "death.attack.onFire.player.by.Steve": "Steve-ом"
}
```

## Установка

Установите мод на клиент. По умолчанию мод использует встроенные склонения для русского языка, которые можно отключить в меню наборов ресурсов. Если ваш язык не поддерживается модом, установите дополнительный набор ресурсов с необходимыми ключами перевода.

## Лицензия

Под лицензией CC0-1.0

</details>

A client-side Fabric mod that allows languages with grammatical cases (such as Russian, Ukrainian, Polish, Czech, Slovak and others) to inflect killer names in Minecraft death messages.

The mod leaves existing Minecraft translations unchanged. It only adds support for extra translation keys, which can be provided by the mod itself (for built-in languages) or by resource packs.

## Examples

<details>
<summary>Russian</summary>

Without the mod:

```
Player был убит Паук
Player был убит Боб
Player был обречён на падение благодаря Паук
Player был сожжён дотла, пока боролся с Steve
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку",
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s",
  "death.attack.onFire.player.by.Steve": "Steve-ом"
}
```

Death messages become:

```
Player был убит пауком
Player был убит Бобом
Player был обречён на падение благодаря пауку
Player был сожжён дотла, пока боролся со Steve-ом
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
Player se nabio na stalagmit dok se borio s Steve
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "pauka",
  "death.attack.player.by.Steve": "Stevea",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.stalagmite.player.by.Steve.message": "%1$s se nabio na stalagmit dok se borio sa %2$s",
  "death.attack.stalagmite.player.by.Steve": "Steveom"
}
```

Death messages become:

```
Player je ubijen od strane pauka
Player je ubijen od strane Stevea
Player je ubijen od strane Boba
Player se nabio na stalagmit dok se borio sa Steveom
```

</details>

<details>
<summary>Polish</summary>

Example only. Not included by default.

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
  "death.attack.wither.player.by.Steve.message": "Gracz %1$s obumarł podczas walki ze %2$s",
  "death.attack.wither.player.by.Steve": "Stevem"
}
```

Death messages become:

```
Gracz Player został zabity przez pająka
Gracz Player został zabity przez Steve'a
Gracz Player został zabity przez Boba
Gracz Player obumarł podczas walki ze Stevem
```

</details>

## Features

- Supports vanilla and modded entities.
- Supports renamed mobs.
- Supports player names.
- Supports all `death.*` messages.
- Uses resource pack translation keys.
- Works entirely on the client.
- Change death message to match entity/player name.
- Configurable through Cloth Config.
- Ability to disable name declension for players and entities in the mod settings.
- Ability to disable message editing in the mod settings.
- Ability to enable hover the original message as a tooltip in the mod settings.
- Falls back to the original name if no declension or message is provided.
- Built-in Russian declension pack with vanilla entities, some names, and some modded entities and death messages (which you can use as examples when translating into your language).

## Translation keys

Examples:

<details>
<summary>Russian</summary>

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.mob.by.Боб": "Бобом",
  "death.fell.assist.by.minecraft.spider": "пауку",
  "death.attack.onFire.player.by.Steve.message": "%1$s был сожжён дотла, пока боролся со %2$s",
  "death.attack.onFire.player.by.Steve": "Steve-ом"
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
  "death.attack.stalagmite.player.by.Steve.message": "%1$s se nabio na stalagmit dok se borio sa %2$s",
  "death.attack.stalagmite.player.by.Steve": "Steveom"
}
```

</details>

<details>
<summary>Polish</summary>

Example only. Not included by default.

```json
{
  "death.attack.mob": "Gracz %1$s został zabity przez %2$s",
  "death.attack.mob.by.minecraft.spider": "pająka",
  "death.attack.player": "Gracz %1$s został zabity przez %2$s",
  "death.attack.player.by.Steve": "Steve'a",
  "death.attack.mob.by.Bob": "Boba",
  "death.attack.wither.player.by.Steve.message": "Gracz %1$s obumarł podczas walki ze %2$s",
  "death.attack.wither.player.by.Steve": "Stevem"
}
```

</details>

## Installation

Install the mod on the client. By default, the mod uses built-in Russian declension entries. They can be disabled in the resource packs screen. If your language isn't supported out of the box, install a resource pack containing the required translation keys.

## License

Licensed under CC0-1.0.