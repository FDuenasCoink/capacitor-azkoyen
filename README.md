# @fduenascoink/capacitor-azkoyen

Capacitor plugin to control azkoyen validator

## Install

```bash
npm install @fduenascoink/capacitor-azkoyen
npx cap sync
```

## API

<docgen-index>

* [`removeListener(...)`](#removelistener)
* [`removeAllListeners(...)`](#removealllisteners)
* [`addListener('coinInsert', ...)`](#addlistenercoininsert)
* [`addListener('coinInsertWarning', ...)`](#addlistenercoininsertwarning)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### removeListener(...)

```typescript
removeListener(event: string) => void & Promise<void>
```

| Param       | Type                |
| ----------- | ------------------- |
| **`event`** | <code>string</code> |

**Returns:** <code>void & Promise&lt;void&gt;</code>

--------------------


### removeAllListeners(...)

```typescript
removeAllListeners(type?: string | undefined) => void & Promise<void>
```

Removes all listeners

| Param      | Type                |
| ---------- | ------------------- |
| **`type`** | <code>string</code> |

**Returns:** <code>void & Promise&lt;void&gt;</code>

--------------------


### addListener('coinInsert', ...)

```typescript
addListener(eventName: 'coinInsert', listenerFunc: (event: CoinEvent) => void) => Promise<PluginListenerHandle> & PluginListenerHandle & string
```

Listens for coin read.

| Param              | Type                                                                |
| ------------------ | ------------------------------------------------------------------- |
| **`eventName`**    | <code>'coinInsert'</code>                                           |
| **`listenerFunc`** | <code>(event: <a href="#coinevent">CoinEvent</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a> & string</code>

--------------------


### addListener('coinInsertWarning', ...)

```typescript
addListener(eventName: 'coinInsertWarning', listenerFunc: (event: CoinEventWarning) => void) => Promise<PluginListenerHandle> & PluginListenerHandle & string
```

Listens for coin read.

| Param              | Type                                                                              |
| ------------------ | --------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'coinInsertWarning'</code>                                                  |
| **`listenerFunc`** | <code>(event: <a href="#coineventwarning">CoinEventWarning</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a> & string</code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### CoinEvent

| Prop        | Type                                            |
| ----------- | ----------------------------------------------- |
| **`error`** | <code>{ code: number; message: string; }</code> |
| **`value`** | <code>number</code>                             |


#### CoinEventWarning

| Prop          | Type                |
| ------------- | ------------------- |
| **`message`** | <code>string</code> |
| **`code`**    | <code>number</code> |

</docgen-api>
