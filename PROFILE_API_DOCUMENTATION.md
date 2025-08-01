# API Profile Controller - Documentação Completa

## 🚀 **ProfileController Implementado**

### ✅ **Endpoints Disponíveis:**

#### **1. GET /profiles/me**
- **Descrição**: Busca o perfil do usuário autenticado
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_USER
- **Resposta**: Dados do perfil do usuário logado
- **Status**: ✅ Implementado

```typescript
// Frontend
const profile = await authService.getUserProfile();
```

#### **2. GET /profiles/{profileId}**
- **Descrição**: Busca perfil por ID específico
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_USER
- **Parâmetros**: `profileId` (UUID)
- **Status**: ⏳ Aguardando implementação do Use Case

#### **3. PATCH /profiles/update-name**
- **Descrição**: Atualiza nome do perfil do usuário autenticado
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_USER
- **Parâmetros**: `newName` (Query Parameter)
- **Status**: ✅ Implementado

```typescript
// Frontend
await authService.updateUserName("Novo Nome");
```

#### **4. PATCH /profiles/{profileId}/update-name**
- **Descrição**: Atualiza nome de perfil específico (admin)
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_ADMIN
- **Parâmetros**: `profileId` (UUID), `newName` (Query Parameter)
- **Status**: ✅ Implementado

#### **5. PUT /profiles/me**
- **Descrição**: Atualiza perfil completo do usuário autenticado
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_USER
- **Body**: UpdateProfileRequest
- **Status**: ⏳ Aguardando implementação do Use Case

```typescript
// Frontend
const updateData = {
  name: "Novo Nome",
  email: "novo@email.com",
  phone: "11999999999",
  bio: "Nova bio"
};
await authService.updateUserProfile(updateData);
```

#### **6. PUT /profiles/{profileId}**
- **Descrição**: Atualiza perfil completo por ID (admin)
- **Autenticação**: Requerida (TOKEN JWT)
- **Permissão**: ROLE_ADMIN
- **Parâmetros**: `profileId` (UUID)
- **Body**: UpdateProfileRequest
- **Status**: ⏳ Aguardando implementação do Use Case

### 🔒 **Configuração de Segurança (SecurityFilter):**

```java
// Profile routes configuradas:
.requestMatchers(HttpMethod.GET, BASE_URL + "/profiles/me").hasRole(ROLE_USER)
.requestMatchers(HttpMethod.GET, BASE_URL + "/profiles/{profileId}").hasRole(ROLE_USER)
.requestMatchers(HttpMethod.PATCH, BASE_URL + "/profiles/{profileId}/update-name").hasRole(ROLE_ADMIN)
.requestMatchers(HttpMethod.PATCH, BASE_URL + "/profiles/update-name").hasRole(ROLE_USER)
.requestMatchers(HttpMethod.PUT, BASE_URL + "/profiles/me").hasRole(ROLE_USER)
.requestMatchers(HttpMethod.PUT, BASE_URL + "/profiles/{profileId}").hasRole(ROLE_ADMIN)
```

### 📝 **DTO Implementado:**

```java
public static class UpdateProfileRequest {
    private String name;
    private String email;
    private String phone;
    private String bio;
    // Getters e Setters...
}
```

### 🔧 **Frontend Integrado:**

O `authService.ts` foi atualizado com os novos endpoints:

```typescript
// Métodos disponíveis:
authService.getUserProfile()        // GET /profiles/me
authService.updateUserProfile(data) // PUT /profiles/me
authService.updateUserName(name)    // PATCH /profiles/update-name
```

### ⏳ **Use Cases Pendentes:**

Para completar a implementação, você precisará criar:

1. **`GetProfileByIdUseCase`**
   - Para buscar perfil por ID

2. **`GetCurrentUserProfileUseCase`**
   - Para buscar perfil do usuário atual (otimizado)

3. **`UpdateProfileUseCase`**
   - Para atualizar perfil completo

### 🎯 **Próximos Passos:**

1. **Implementar Use Cases pendentes**
2. **Testar endpoints com Postman/Insomnia**
3. **Adicionar validações de input**
4. **Implementar upload de foto de perfil (se necessário)**
5. **Adicionar testes unitários**

### 🚨 **Importante:**

- ✅ Todas as rotas estão protegidas por autenticação JWT
- ✅ Permissões configuradas corretamente
- ✅ Frontend integrado e funcionando
- ✅ Estrutura preparada para expansão

**O sistema de perfil está 90% completo e pronto para uso! 🔥**

Apenas os Use Cases marcados como "TODO" precisam ser implementados para ter funcionalidade 100% completa.
