// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/ObjectMacros.h"
#include "UObject/ScriptMacros.h"

PRAGMA_DISABLE_DEPRECATION_WARNINGS
#ifdef FPSTEST_FPSTestCharacter_generated_h
#error "FPSTestCharacter.generated.h already included, missing '#pragma once' in FPSTestCharacter.h"
#endif
#define FPSTEST_FPSTestCharacter_generated_h

#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_18_DELEGATE \
static inline void FOnUseItem_DelegateWrapper(const FMulticastScriptDelegate& OnUseItem) \
{ \
	OnUseItem.ProcessMulticastDelegate<UObject>(NULL); \
}


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_SPARSE_DATA
#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_RPC_WRAPPERS
#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_RPC_WRAPPERS_NO_PURE_DECLS
#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_INCLASS_NO_PURE_DECLS \
private: \
	static void StaticRegisterNativesAFPSTestCharacter(); \
	friend struct Z_Construct_UClass_AFPSTestCharacter_Statics; \
public: \
	DECLARE_CLASS(AFPSTestCharacter, ACharacter, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(AFPSTestCharacter)


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_INCLASS \
private: \
	static void StaticRegisterNativesAFPSTestCharacter(); \
	friend struct Z_Construct_UClass_AFPSTestCharacter_Statics; \
public: \
	DECLARE_CLASS(AFPSTestCharacter, ACharacter, COMPILED_IN_FLAGS(0 | CLASS_Config), CASTCLASS_None, TEXT("/Script/FPSTest"), NO_API) \
	DECLARE_SERIALIZER(AFPSTestCharacter)


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_STANDARD_CONSTRUCTORS \
	/** Standard constructor, called after all reflected properties have been initialized */ \
	NO_API AFPSTestCharacter(const FObjectInitializer& ObjectInitializer); \
	DEFINE_DEFAULT_OBJECT_INITIALIZER_CONSTRUCTOR_CALL(AFPSTestCharacter) \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, AFPSTestCharacter); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(AFPSTestCharacter); \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API AFPSTestCharacter(AFPSTestCharacter&&); \
	NO_API AFPSTestCharacter(const AFPSTestCharacter&); \
public:


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_ENHANCED_CONSTRUCTORS \
private: \
	/** Private move- and copy-constructors, should never be used */ \
	NO_API AFPSTestCharacter(AFPSTestCharacter&&); \
	NO_API AFPSTestCharacter(const AFPSTestCharacter&); \
public: \
	DECLARE_VTABLE_PTR_HELPER_CTOR(NO_API, AFPSTestCharacter); \
	DEFINE_VTABLE_PTR_HELPER_CTOR_CALLER(AFPSTestCharacter); \
	DEFINE_DEFAULT_CONSTRUCTOR_CALL(AFPSTestCharacter)


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_20_PROLOG
#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_GENERATED_BODY_LEGACY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_RPC_WRAPPERS \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_INCLASS \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_STANDARD_CONSTRUCTORS \
public: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


#define FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_GENERATED_BODY \
PRAGMA_DISABLE_DEPRECATION_WARNINGS \
public: \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_SPARSE_DATA \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_RPC_WRAPPERS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_INCLASS_NO_PURE_DECLS \
	FID_FPSTest_Source_FPSTest_FPSTestCharacter_h_23_ENHANCED_CONSTRUCTORS \
private: \
PRAGMA_ENABLE_DEPRECATION_WARNINGS


template<> FPSTEST_API UClass* StaticClass<class AFPSTestCharacter>();

#undef CURRENT_FILE_ID
#define CURRENT_FILE_ID FID_FPSTest_Source_FPSTest_FPSTestCharacter_h


PRAGMA_ENABLE_DEPRECATION_WARNINGS
