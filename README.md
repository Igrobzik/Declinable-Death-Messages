# Declinable Death Messages

A client-side Fabric mod that allows languages with grammatical cases (such as Russian, Ukrainian, Polish, Czech, Slovak and others) to inflect killer names in Minecraft death messages.

## Example

Without the mod:

```
Player был убит Паук
Player был убит Steve
Player был обречён на падение благодаря Паук
```

With a resource pack:

```json
{
  "death.attack.mob.by.minecraft.spider": "пауком",
  "death.attack.player.by.Steve": "Steve-ом",
  "death.fell.assist.by.minecraft.spider": "пауку"
}
```

Death messages become:

```
Player был убит пауком
Player был убит Steve-ом
Player был обречён на падение благодаря пауку
```

## Features

- Supports vanilla and modded entities.
- Supports renamed mobs.
- Supports player names.
- Supports all `death.attack.*` and `death.fell.*` messages.
- Uses resource pack translation keys.
- Works entirely on the client.
- Falls back to the original name if no declension is provided.

## Translation keys

Examples:

```json
{
  "death.attack.mob.by.minecraft.spider": "spider",
  "death.attack.player.by.PlayerName": "PlayerName-em",
  "death.fell.assist.by.minecraft.spider": "spider"
}
```

## Installation

Install the mod on the client and use a resource pack containing the required translation keys.

## License

CC0-1.0