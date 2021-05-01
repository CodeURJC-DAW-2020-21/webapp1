import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerSignUpFormComponent } from './player-sign-up-form.component';

describe('PlayerSignUpFormComponent', () => {
  let component: PlayerSignUpFormComponent;
  let fixture: ComponentFixture<PlayerSignUpFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlayerSignUpFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerSignUpFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
