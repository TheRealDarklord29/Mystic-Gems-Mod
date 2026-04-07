# `mixin` package guide

Mixin classes for integrating with Minecraft internals.

## Files
- `PlayerEntityMixin`: persists player gem data in NBT.

## Notes
- Only add mixins when needed for behavior that cannot be done via API/events.
- Keep mixins focused and minimal; avoid broad invasive injections.
